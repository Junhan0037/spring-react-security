import SignIn from "../components/SignIn";
import {useCallback, useEffect, useState} from "react";
import {useDispatch, useSelector} from "react-redux";
import {signin as signinSagaStart} from "../redux-saga/modules/auth";
import {AuthErrorType, RootState} from "../types";

export default function SignInContainer(){
    const [error, setError] = useState<AuthErrorType | null>(null);
    const dispatch = useDispatch();
    const authError = useSelector((state: RootState )=>state.auth.authError);

    useEffect(()=>{
        if(authError) {
            setError(authError);
        }
    }, [authError])

    const signin = useCallback((reqData) => {
        dispatch(signinSagaStart(reqData))
    }, [dispatch]);
    return <SignIn signin={signin} error={error}/>
}