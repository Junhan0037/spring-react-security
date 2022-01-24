import SignIn from "../components/SignIn";
import {useCallback, useEffect, useState} from "react";
import {useDispatch, useSelector} from "react-redux";
import {signup as signupSagaStart, SIGNUP} from "../redux-saga/modules/auth";
import {SignUpErrorType, RootState} from "../types";
import SignUp from "../components/SignUp";

export default function SignUpContainer(){
    const [error, setError] = useState<SignUpErrorType | null>(null);
    const dispatch = useDispatch();
    const {signUpError, isLoading} = useSelector((state: RootState )=>({
        signUpError: state.auth.signUpError,
        isLoading: state.loading[SIGNUP],
    }));

    useEffect(()=>{
        if(signUpError) {
            setError(signUpError);
        }
    }, [signUpError])

    const signup = useCallback((reqData) => {
        dispatch(signupSagaStart(reqData))
    }, [dispatch]);

    return <SignUp signup={signup} error={error} isLoading={isLoading}/>
}