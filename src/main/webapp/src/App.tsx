import React from 'react';
import {Route, Switch} from 'react-router-dom';
import {ConnectedRouter} from "connected-react-router";
import history from "./history";
import {ErrorBoundary} from 'react-error-boundary';
import loadable from '@loadable/component'

import Error from './pages/ErrorPage';
import Loading from './components/Loading';

const Home =  loadable(() => import('./pages/HomePage'), {fallback: <Loading/>});
const SignIn = loadable(() => import('./pages/SignInPage'), {fallback: <Loading/>});
const SignUp = loadable(() => import('./pages/SignUpPage'), {fallback: <Loading/>});
const NotFound = loadable(() => import('./pages/NotFound'), {fallback: <Loading/>});
const Jippagui = loadable(() => import('./pages/JippaguiPage'), {fallback: <Loading/>});
const BDCrew = loadable(() => import("./pages/BDCrewPage"), {fallback: <Loading/>});
const FindPassword = loadable(() => import('./pages/FindPasswordPage'), {fallback: <Loading/>});

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