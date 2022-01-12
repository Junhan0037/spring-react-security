import {AnyAction, Reducer} from "redux";
import {RouterState} from "connected-react-router";

export type SignInResponseType = {
    createdDate: string | null,
    modifiedDate: string | null,
    id: number,
    userId: string | null,
    userPassword: null,
    name: string | null,
    email: string | null,
    role: string | null,
}

export type UserInfoType = {
    jsessionid: string | null,
    userId: string | null,
    name: string | null,
}

export type SignInReqType = {
    userId: FormDataEntryValue | null;
    userPassword: FormDataEntryValue | null;
}

export type AuthErrorType = {
    message: string | null,
    errorMessage: string | null,
    errorDetailMessage: string | null,
}

export interface AuthState {
    isSigninedIn: boolean,
    userInfo: UserInfoType | {},
    // token: string | null;
    // loading: boolean;
    error: AuthErrorType | null;
}

export interface RootState{
    auth: AuthState;
    router: Reducer<RouterState<unknown>, AnyAction>;
}