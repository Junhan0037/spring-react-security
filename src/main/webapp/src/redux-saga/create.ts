import {applyMiddleware, createStore} from "redux";
import {composeWithDevTools} from "redux-devtools-extension";
import rootReducer from "./modules";
import createSagaMiddleware from 'redux-saga';
import rootSaga from "./modules/rootSaga";
import {routerMiddleware} from 'connected-react-router';
import history from "../history";
import cookies from "../lib/cookies";

const create = () => {
    // const initToken = TokenService.get();
    const initUserInfo = cookies.get('userInfo');
    const initIsSigninedIn = (initUserInfo===null || initUserInfo===undefined) ? false : true;

    const sagaMiddleware = createSagaMiddleware();

    const store = createStore(
        rootReducer(history),
        {
            auth: {
                isSigninedIn: initIsSigninedIn,
                userInfo: (initUserInfo === null || initUserInfo === undefined) ?
                    null
                    :
                    {
                        jsessionid: cookies.get('JSESSIONID'),
                        userId: initUserInfo.userId,
                        name: initUserInfo.name,
                    },
                // token: initToken,
                // loading: false,
                authError: null,
            }},
        composeWithDevTools(applyMiddleware(sagaMiddleware, routerMiddleware(history)))
    );

    sagaMiddleware.run(rootSaga);

    return store;
}
export default create;