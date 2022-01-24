import {AnyAction, Reducer} from "redux";
import {RouterState} from "connected-react-router";



// export type SignInResponseType = {
//     createdDate: string | null,
//     modifiedDate: string | null,
//     id: number,
//     userId: string | null,
//     userPassword: null,
//     name: string | null,
//     email: string | null,
//     role: string | null,
// }

export interface SignUpResponseType {
    data: {
        "userId": string | null,
        "name": string | null,
        "email": string | null,
        "role": string | null,
    },
}

export type SignInResponseType = {
    data: {
        createdDate: string | null,
        modifiedDate: string | null,
        id: number,
        userId: string | null,
        userPassword: null,
        name: string | null,
        email: string | null,
        role: string | null,
    },
}

export type SignUpReqType = {
    email: FormDataEntryValue | null;
    name: FormDataEntryValue | null;
    userId: FormDataEntryValue | null;
    userPassword: FormDataEntryValue | null;
    userPasswordConfirm: FormDataEntryValue | null;
}

export type SignInReqType = {
    userId: FormDataEntryValue | null;
    userPassword: FormDataEntryValue | null;
}

export interface SignUpProps {
    signup: (reqData: SignUpReqType) => void;
    error: SignUpErrorType | null;
    isLoading: boolean;
}

export interface SignInProps {
    signin: (reqData: SignInReqType) => void;
    error: SignInErrorType | null;
    isLoading: boolean;
}

// export type AuthErrorType = {
//     status: number | null,
//     statusText: string | null,
//     message: string | null,
//     errorMessage: string | null,
//     errorDetailMessage: string | null,
// }

export type SignUpErrorType = {
    exception: string | null,
    code: string | null,
    message: string | null,
    status: number | null,
    error: string | null,
}

export type SignInErrorType = {
    status: number | null,
    statusText: string | null,
    message: string | null,
    // errorMessage: string | null,
    // errorDetailMessage: string | null,
}

export interface UserInfoType {
    jsessionid: string | null,
    userId: string | null,
    name: string | null,
}

// export interface AuthState {
//     isSigninedIn: boolean,
//     userInfo: UserInfoType | null,
//     // token: string | null;
//     // loading: boolean;
//     authError: AuthErrorType | null;
// }

export interface AuthState {
    isSigninedIn: boolean,
    userInfo: UserInfoType | null,
    // token: string | null;
    // loading: boolean;
    // authError: SignUpErrorType | SignInErrorType | null,
    authErrorCase: string | null,
    signInError: SignInErrorType | null,
    signUpError: SignUpErrorType | null,
}

export interface RootState{
    loading: {[key: string]: boolean};
    auth: AuthState;
    router: Reducer<RouterState<unknown>, AnyAction>;
}