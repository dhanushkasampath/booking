import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
// import EmployeeRegistration from './components/EmployeeRegistration';
import {BrowserRouter, Navigate, Route, Routes} from "react-router-dom";
import HomePage from "./pages/HomePage";
import AuthPage from "./pages/AuthPage";
import ProfilePage from "./pages/ProfilePage";
import Layout from "./components/Layout/Layout";

function App() {
    // return (<div>Hellow</div>)
    return (
        <BrowserRouter>
            <Routes>
                <Route path='/' exact>
                    <HomePage />
                </Route>

                <Route path='/auth'>
                    <AuthPage />
                </Route>

                <Route path='/profile'>
                    <ProfilePage />
                </Route>

                {/*<Route path='*'>*/}
                {/*    <Navigate to='/' />*/}
                {/*</Route>*/}
            </Routes>
        </BrowserRouter>
    )
  // return (
  //   <div className="App">
  //     <EmployeeRegistration/>
  //
  //
  //     <div className='bannerback'></div>
  //
  //
  //     <div className='main_header_hotel' >Hotel</div>
  //
  //     <div className='main_header_name'>TRAVO HOTEL BOOKING</div>
  //      <div className='main_header_restuarents'>Restuarents</div>
  //      <div className='main_header_cab'>Cab</div>
  //      <div className='downloadcircle'></div>
  //
  //
  //      <div className='download_content'>Download mobile app</div>
  //
  //      <div className='bannerbottom'></div>
  //
  //      <div className='fotter'>
  //   <div className='footerback'></div>
  //   <div className='name'>TRAVO HOTEL BOOKING</div>
  //   <div className='about'>About TRAVO HOTEL BOOKING</div>
  //   <div className='support'>Support center</div>
  //   <div className='supportcentercontent'>Not Found What You Search <br/> <br/> How To Book? <br/> <br/> Why Alpha Hotel Booking? <br/> <br/> FAQs </div>
  //   <div className='becomehost'></div>
  //   <div className='becomehostcontent'>Become a Host</div>
  //   <div className='signup'></div>
  //   <div className='signupcontent'>Sign Up</div>
  //   <div className='fotterline'></div>
  //   <div className='download'>Download available from</div>
  //   <div className='downloadbox'></div>
  //   <div className='last_row'>© 2022 Angular Whiplash | All rights reserved | Terms and conditions | Privacy</div>
  //   <div className='fa-brands fa-facebook'></div>
  //
  //   </div>
  //
  //   </div>
  // );
}

export default App;
