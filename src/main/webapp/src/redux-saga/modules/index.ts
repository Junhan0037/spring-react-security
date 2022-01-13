import {combineReducers} from "redux";
import auth from "./auth";
import loading from './loading';
import {connectRouter} from "connected-react-router";
import {History} from 'history';

const rootReducer = (history: History) =>
    combineReducers({
        auth,
        loading,
        router: connectRouter(history),
    });

export default rootReducer;