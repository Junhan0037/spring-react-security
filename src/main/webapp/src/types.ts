import {AnyAction, Reducer} from "redux";
import {RouterState} from "connected-react-router";

export type SignInReqType = {
    userId: FormDataEntryValue | null;
    userPassword: FormDataEntryValue | null;
}

export interface AuthState {
    token: string | null;
    loading: boolean;
    error: Error | null;
}

export interface RootState{
    auth: AuthState;
    router: Reducer<RouterState<unknown>, AnyAction>;
}