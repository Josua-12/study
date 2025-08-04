import logo from './logo.svg';
import './App.css';
import Dog from './Dog';
import Cat from './Cat';
import Header from './components/Header';
import Slider from './components/Slider';
import Main from './components/Main';
import Footer from './components/Footer';

function App() {
  return (
    <div className="App">
      <Header/>
      <Slider style={{width:"100%", color:"white", height:"200px", background:"gold"}}></Slider>
      <Main/>
      <Footer/>
    </div>
  );
}

export default App;
