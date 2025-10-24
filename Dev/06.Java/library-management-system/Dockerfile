# Stage 1 : Build (빌드 단계)
# - 결과물 : JAR 파일 (build\libs\*.jar)

# gradle 8.5와 JDK 17이 포함된 공식 이미지를 빌드 스테이지의 베이스로 이용
FROM gradle:8.5-jdk17 AS builder
# 컨테이너 내 작업 디렉토리를 /app으로 설정 (모든 후속 명령이 이 디렉토리에서 실행됨)
WORKDIR /app

# Gradle 빌드 설정 파일들을 컨테이너로 복사
COPY build.gradle settings.gradle ./
# Gradle wrapper 관련 파일들을 컨테이너로 복사
COPY gradle ./gradle

# 프로젝트 의존성을 미리 다운로드
RUN gradle dependencies --no-daemon

# 소스 코드 복사
COPY src ./src

# 애플리케이션 빌드
# clean : 이전 빌드 산출물 제거
RUN gradle clean build -x test --no-daemon
# --------------------------------------------------------------------
# Stage 2 : Runtime (실행 단계)
# - 목적 : 빌드된 JAR 파일만 포함한 경량 실행 이미지 생성
# - Base Image : 경량 Alpine Linux 기반 JRE
# - 최종 이미지 크기 : 400MB 내외

# 경량화된 Alpine Linux 기반 JRE 17 이미지를 런타임 스테이지의 베이스로 사용
FROM eclipse-temurin:17-jre-alpine
# 컨테이너 내 작업 디렉토리 /app으로 설정
WORKDIR /app

# 보안을 위해 non-root 사용자(spring)와 그룹(spring)을 생성
# 컨테이너를 root가 아닌 일반 사용자로 실행하여 보안 강화
# -S : 시스템 그룹, 시스템 사용자 생성 (root 권한 필요)
RUN addgroup -S spring && adduser -S spring -G spring

# 디렉토리 생성 및 소유권 설정
RUN mkdir -p /app/uploads && chown -R spring:spring /app

# 빌드 단계에서 생성된 JAR 파일 복사
# COPY --from=builder : 이전 빌드 스테이지에서 파일 복사
COPY --from=builder /app/build/libs/*.jar app.jar

# JAR 파일 소유권을 spring 사용자로 변경
RUN chown spring:spring /app/app.jar

# 사용자 전환
USER spring:spring

#포트 노출
EXPOSE 8081

HEALTHCHECK --interval=30s --timeout=3s --start-period=60s --retries=3 \
CMD wget --no-verbose --tries=1 --spider http://localhost:8081/actuator/health || exit 1

# 애플리케이션 실행
ENTRYPOINT ["java", \
    "-Djava.security.egd=file:/dev/./urandom", \
    "-Dspring.profiles.active=${SPRING_PROFILES_ACTIVE:-prod}", \
    "-jar", \
    "app.jar"]
