import SignIn from "../components/SignIn";
import {useCallback, useEffect, useState} from "react";
import {useDispatch, useSelector} from "react-redux";
import {signin as signinSagaStart, SIGNIN} from "../redux-saga/modules/auth";
import {AuthErrorType, RootState} from "../types";

export default function SignInContainer(){
    const [error, setError] = useState<AuthErrorType | null>(null);
    const dispatch = useDispatch();
    const {authError, isLoading} = useSelector((state: RootState )=>({
        authError: state.auth.authError,
        isLoading: state.loading[SIGNIN],
    }));

    useEffect(()=>{
        if(authError) {
            setError(authError);
        }
    }, [authError])

    const signin = useCallback((reqData) => {
        dispatch(signinSagaStart(reqData))
    }, [dispatch]);
    return <SignIn signin={signin} error={error} isLoading={isLoading}/>
}