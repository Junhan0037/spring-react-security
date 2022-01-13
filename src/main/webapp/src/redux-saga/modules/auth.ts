import {Action, handleActions, createAction} from "redux-actions";
import {call, put, takeEvery} from "redux-saga/effects";
import {SignInReqType, AuthState, SignInResponseType} from "../../types";
import {push} from "connected-react-router";
import cookies from "../../lib/cookies";
import createRequestActionTypes from "../../lib/createRequestActionTypes";
import * as authAPI from '../../lib/api/auth';
import {finishLoading, startLoading} from "./loading";

export const [SIGNIN, SIGNIN_SUCCESS, SIGNIN_FAILURE] = createRequestActionTypes(
    'auth/SIGNIN'
);
export const LOGOUT = 'auth/LOGOUT';


export const signin = createAction(SIGNIN, ({userId, userPassword}: SignInReqType) => ({
    userId,
    userPassword
}));
export const logout = createAction(LOGOUT);


function* signinSaga(action: Action<SignInReqType>) {
    yield put(startLoading(SIGNIN)); // 로딩 시작
    try {
        const signInRespData: SignInResponseType = yield call(authAPI.signin, action.payload);

        yield put({
            type: SIGNIN_SUCCESS,
            payload: signInRespData,
        });

        // cookies.set('userInfo', window.btoa('bdcrew junhan X yeboong('+JSON.stringify(signInRespData)+')'));
        cookies.set('userInfo', {
            userId: signInRespData.data.userId,
            name: signInRespData.data.name,
        });

        // push
        yield put(push('/'))

    } catch (error) {
        // console.log(error)
        yield put({
            type: SIGNIN_FAILURE,
            payload: error,
        });
    }
    yield put(finishLoading(SIGNIN)); // 로딩 끝
}

function* logoutSaga() {
    try {
        yield call(authAPI.logout);
    } catch (error) {

    } finally {
        cookies.remove('JSESSIONID');
        cookies.remove('userInfo');
    }
}

export function* authSaga() {
    yield takeEvery(LOGOUT, logoutSaga);
    yield takeEvery(SIGNIN, signinSaga);
}

const initState: AuthState = {
    isSigninedIn: false,
    userInfo: null,
    authError: null,
}

const auth = handleActions<AuthState, any>({
    [SIGNIN_SUCCESS]: (state, action) => ({
        ...state,
        isSigninedIn: true,
        userInfo: {
            jsessionid: cookies.get('JSESSIONID'),
            userId: action.payload.data.userId,
            name: action.payload.data.name,
        },
        authError: null,
    }),
    [SIGNIN_FAILURE]: (state, action) => ({
        ...state,
        isSigninedIn: false,
        userInfo: null,
        authError: {
            status: action.payload.status,
            statusText: action.payload.statusText,
            message: action.payload.data.message,
            errorMessage: action.payload.data.errorMessage,
            errorDetailMessage: action.payload.data.errorDetailMessage,
        },
    }),
    [LOGOUT]: state => ({
        ...state,
        isSigninedIn: false,
        userInfo: null,
        authError: null,
    })
}, initState);

export default auth;



