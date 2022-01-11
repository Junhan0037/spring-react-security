import React from 'react';
import {Route, Switch} from 'react-router-dom';
import Home from './pages/HomePage';
import SignIn from './pages/SignInPage';
import SignUp from './pages/SignUpPage';
import NotFound from './pages/NotFound';
import Jippagui from './pages/JippaguiPage';
import BDCrew from "./pages/BDCrewPage";
import Error from './pages/ErrorPage';
import FindPassword from './pages/FindPasswordPage';
import {ConnectedRouter} from "connected-react-router";
import history from "./history";
import {ErrorBoundary} from 'react-error-boundary';

function App() {
    return (
        <ErrorBoundary FallbackComponent={Error}>
            <ConnectedRouter history={history}>
                <Switch>
                    <Route exact path="/find-pwd" component={FindPassword}/>
                    <Route exact path="/signup" component={SignUp}/>
                    <Route exact path="/signin" component={SignIn}/>
                    <Route exact path="/" component={Home}/>
                    <Route exact path="/jippagui" component={Jippagui}/>
                    <Route exact path="/bd-crew" component={BDCrew}/>
                    <Route component={NotFound}/>
                </Switch>
            </ConnectedRouter>
        </ErrorBoundary>

    )
}

export default App;