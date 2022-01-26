import {AnyAction, Reducer} from "redux";
import {RouterState} from "connected-react-router";
import {ChangeEvent, ChangeEventHandler} from "react";
import * as React from "react";


export interface ChangeFieldType {
    form: string;
    key: string;
    value: string;
    [index: string]: any;
}

export interface SignUpResponseType {
    data: {
        "userId": string | null,
        "name": string | null,
        "email": string | null,
        "role": string | null,
    },
}

export interface SignInResponseType {
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

export interface SignUpReqType {
    email: FormDataEntryValue | null;
    name: FormDataEntryValue | null;
    userId: FormDataEntryValue | null;
    userPassword: FormDataEntryValue | null;
    userPasswordConfirm: FormDataEntryValue | null;
    [index: string]: any;
}

export interface SignInReqType {
    userId: FormDataEntryValue | null;
    userPassword: FormDataEntryValue | null;
    [index: string]: any;
}

export interface SignUpProps {
    form: SignUpReqType;
    signup: (reqData: SignUpReqType) => void;
    error: SignUpErrorType | null;
    isLoading: boolean;
    onChange: (e: React.ChangeEvent<HTMLInputElement>) => void;
    handleSubmit: (e: React.FormEvent<HTMLFormElement>) => void;
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

export interface SignUpErrorType {
    exception: string | null,
    code: string | null,
    message: string | null,
    status: number | null,
    error: string | null,
}

export interface SignInErrorType {
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
    signup: SignUpReqType,
    signin: SignInReqType,
    isSigninedIn: boolean,
    userInfo: UserInfoType | null,
    // token: string | null;
    // loading: boolean;
    // authError: SignUpErrorType | SignInErrorType | null,
    authErrorCase: string | null,
    signInError: SignInErrorType | null,
    signUpError: SignUpErrorType | null,
    [index: string]: any;
}

export interface RootState{
    loading: {[key: string]: boolean};
    auth: AuthState;
    router: Reducer<RouterState<unknown>, AnyAction>;
}