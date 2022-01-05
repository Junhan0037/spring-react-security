import React from 'react';
import { BrowserRouter, Route, Routes }from 'react-router-dom';
import Home from './pages/HomePage';
import SignIn from './pages/SignInPage';
import SignUp from './pages/SignUpPage';
import NotFound from './pages/NotFound';
import BD from './pages/BDPage';
import FindPassword from './pages/FindPasswordPage';

function App() {
  return (
    <BrowserRouter>
        <Routes>
          <Route path="/find-pwd" element={<FindPassword />} />
          <Route path="/signup" element={<SignUp />} />
          <Route path="/signin" element={<SignIn />} />
          <Route path="/" element={<Home />} />
          <Route element={<NotFound />} />
          <Route path="/jippagui" element={<BD />} />
        </Routes>
    </BrowserRouter>
  )
}

export default App;