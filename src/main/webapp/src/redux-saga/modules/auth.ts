import {Action, handleActions, createAction} from "redux-actions";
import {call, put, takeEvery} from "redux-saga/effects";
import {SignInReqType, AuthState, SignInResponseType, SignUpReqType, SignUpResponseType} from "../../types";
import {push} from "connected-react-router";
import cookies from "../../lib/cookies";
import createRequestActionTypes from "../../lib/createRequestActionTypes";
import * as authAPI from '../../lib/api/auth';
import {finishLoading, startLoading} from "./loading";

export const [SIGNUP, SIGNUP_SUCCESS, SIGNUP_FAILURE] = createRequestActionTypes(
    'auth/SIGNUP'
);
export const [SIGNIN, SIGNIN_SUCCESS, SIGNIN_FAILURE] = createRequestActionTypes(
    'auth/SIGNIN'
);
export const LOGOUT = 'auth/LOGOUT';


export const signup = createAction(SIGNUP, ({email, name, userId, userPassword, userPasswordConfirm}: SignUpReqType) => ({
    email,
    name,
    userId,
    userPassword,
    userPasswordConfirm
}));
export const signin = createAction(SIGNIN, ({userId, userPassword}: SignInReqType) => ({
    userId,
    userPassword
}));
export const logout = createAction(LOGOUT);


function* signupSaga(action: Action<SignUpReqType>) {
    yield put(startLoading(SIGNUP)); // 로딩 시작
    try {
        const signUpRespData: SignUpResponseType = yield call(authAPI.signup, action.payload);

        yield put({
            type: SIGNUP_SUCCESS,
            payload: signUpRespData,
        });

        // 유저정보 쿠키에 담기
        // cookies.set('userInfo', window.btoa('bdcrew junhan X yeboong('+JSON.stringify(signInRespData)+')'));
        cookies.set('userInfo', {
            // jsessionid: cookies.get('SPRING_REACT_SECURITY_JSESSIONID'),
            userId: signUpRespData.data.userId,
            name: signUpRespData.data.name,
        });

        // push
        yield put(push('/'))

    } catch (error) {
        // console.log(error)
        yield put({
            type: SIGNUP_FAILURE,
            payload: error,
        });
    }
    yield put(finishLoading(SIGNUP)); // 로딩 끝
}

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
        cookies.remove('SPRING_REACT_SECURITY_JSESSIONID');
        cookies.remove('userInfo');
    }
}

export function* authSaga() {
    yield takeEvery(SIGNIN, signinSaga);
    yield takeEvery(SIGNUP, signupSaga);
    yield takeEvery(LOGOUT, logoutSaga);
}

const initState: AuthState = {
    isSigninedIn: false,
    userInfo: null,
    authErrorCase: null,
    signInError: null,
    signUpError: null,
}

const auth = handleActions<AuthState, any>({
    [SIGNUP_SUCCESS]: (state, action) => ({
        ...state,
        isSigninedIn: true,
        userInfo: {
            jsessionid: cookies.get('SPRING_REACT_SECURITY_JSESSIONID'),
            userId: action.payload.data.userId,
            name: action.payload.data.name,
        },
        // authError: null,
        authErrorCase: null,
        signInError: null,
        signUpError: null,
    }),
    [SIGNUP_FAILURE]: (state, action) => ({
        ...state,
        isSigninedIn: false,
        userInfo: null,
        authErrorCase: "signup",
        signUpError: {
            exception: action.payload.data.exception,
            code: action.payload.data.code,
            message: action.payload.data.message,
            status: action.payload.data.status,
            error: action.payload.data.error,
        },
        signInError: null,
        // authError: {
        //     exception: action.payload.data.exception,
        //     code: action.payload.data.code,
        //     message: action.payload.data.message,
        //     status: action.payload.data.status,
        //     error: action.payload.data.error,
        // },
    }),
    [SIGNIN_SUCCESS]: (state, action) => ({
        ...state,
        isSigninedIn: true,
        userInfo: {
            jsessionid: cookies.get('SPRING_REACT_SECURITY_JSESSIONID'),
            userId: action.payload.data.userId,
            name: action.payload.data.name,
        },
        // authError: null,
        authErrorCase: null,
        signInError: null,
        signUpError: null,
    }),
    [SIGNIN_FAILURE]: (state, action) => ({
        ...state,
        isSigninedIn: false,
        userInfo: null,
        // authError: {
        //     status: action.payload.status,
        //     statusText: action.payload.statusText,
        //     message: action.payload.data.message,
        //     errorMessage: action.payload.data.errorMessage,
        //     errorDetailMessage: action.payload.data.errorDetailMessage,
        // },
        authErrorCase: "signin",
        signInError: {
            status: action.payload.status,
            statusText: action.payload.statusText,
            message: action.payload.data.message,
            errorMessage: action.payload.data.errorMessage,
            errorDetailMessage: action.payload.data.errorDetailMessage,
        },
        signUpError: null,
    }),
    [LOGOUT]: state => ({
        ...state,
        isSigninedIn: false,
        userInfo: null,
        // authError: null,
        authErrorCase: null,
        signInError: null,
        signUpError: null,
    })
}, initState);

export default auth;



