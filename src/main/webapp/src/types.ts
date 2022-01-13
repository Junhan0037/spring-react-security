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

export interface UserInfoType {
    jsessionid: string | null,
    userId: string | null,
    name: string | null,
}

export type SignInReqType = {
    userId: FormDataEntryValue | null;
    userPassword: FormDataEntryValue | null;
}

export type AuthErrorType = {
    status: number | null,
    statusText: string | null,
    message: string | null,
    errorMessage: string | null,
    errorDetailMessage: string | null,
}

export interface AuthState {
    isSigninedIn: boolean,
    userInfo: UserInfoType | null,
    // token: string | null;
    // loading: boolean;
    authError: AuthErrorType | null;
}

export interface RootState{
    loading: {[key: string]: boolean};
    auth: AuthState;
    router: Reducer<RouterState<unknown>, AnyAction>;
}

export interface SignInProps {
    signin: (reqData: SignInReqType) => void;
    error: AuthErrorType | null;
    isLoading: boolean;
}