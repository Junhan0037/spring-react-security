import {AnyAction, Reducer} from "redux";
import {RouterState} from "connected-react-router";

export type SignInReqType = {
    email: FormDataEntryValue | null;
    password: FormDataEntryValue | null;
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