import SignIn from "../components/SignIn";
import {useCallback, useEffect, useState} from "react";
import {useDispatch, useSelector} from "react-redux";
import {signin as signinSagaStart, SIGNIN} from "../redux-saga/modules/auth";
import {SignInErrorType, RootState} from "../types";

export default function SignInContainer(){
    const [error, setError] = useState<SignInErrorType | null>(null);
    const dispatch = useDispatch();
    const {signInError, isLoading} = useSelector((state: RootState )=>({
        signInError: state.auth.signInError,
        isLoading: state.loading[SIGNIN],
    }));

    useEffect(()=>{
        if(signInError) {
            setError(signInError);
            console.log(signInError)
        }
    }, [signInError])

    const signin = useCallback((reqData) => {
        dispatch(signinSagaStart(reqData))
    }, [dispatch]);
    return <SignIn signin={signin} error={error} isLoading={isLoading}/>
}