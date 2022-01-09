import {Action, createActions, handleActions} from "redux-actions";
import {call, put, takeEvery, select} from "redux-saga/effects";
import {SignInReqType, AuthState} from "../../types";
import UserService from "../../services/UserService";
import TokenService from "../../services/TokenService";
import {push} from "connected-react-router";

const initState: AuthState = {
    token: null,
    loading: false,
    error: null,
}

const prefix = 'bdcrew/auth';

export const {pending, success, fail} = createActions(
    'PENDING',
    'SUCCESS',
    'FAIL',
    {prefix}
);

const auth = handleActions<AuthState, string>({
    PENDING: (state) => ({
        ...state,
        loading: true,
        error: null,
    }),
    SUCCESS: (state, action) => ({
        ...state,
        token: action.payload,
        loading: false,
        error: null,
    }),
    FAIL: (state, action: any) => ({
        ...state,

        loading: false,
        error: action.payload,
    }),
}, initState, {prefix});

export default auth;

// saga
export const {signin, logout} = createActions(
    'SIGNIN',
    'LOGOUT',
    {prefix}
);

function* signinSaga(action: Action<SignInReqType>) {
    try {
        yield put(pending());
        const token: string = yield call(UserService.signin, action.payload);

        // local storage
        TokenService.set(token);

        yield put(success(token));

        // push
        yield put(push('/'))

    } catch (error: any) {
        yield put(fail(new Error(error?.response?.data?.error || 'UNKNOWN ERROR')))
    }
}

function* logoutSaga() {
    try {
        yield put(pending());

        const token: string = yield select((state) => state.auth.token);
        yield call(UserService.logout, token);

        // local storage
        TokenService.set(token);

    } catch (error) {

    } finally {
        TokenService.remove();
        yield put(success(null));
    }
}

export function* authSaga() {
    yield takeEvery(`${prefix}/SIGNIN`, signinSaga);
    yield takeEvery(`${prefix}/LOGOUT`, logoutSaga);
}