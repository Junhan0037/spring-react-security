import {useCallback, useEffect, useState} from "react";
import {useDispatch, useSelector} from "react-redux";
import {changeField, initializeForm, signup as signupSagaStart, SIGNUP} from "../redux-saga/modules/auth";
import {SignUpErrorType, RootState} from "../types";
import SignUp from "../components/SignUp";
import * as React from "react";

export default function SignUpContainer(){
    const [error, setError] = useState<SignUpErrorType | null>(null);
    const dispatch = useDispatch();
    const {form ,signUpError, isLoading} = useSelector((state: RootState )=>({
        form: state.auth.signup,
        signUpError: state.auth.signUpError,
        isLoading: state.loading[SIGNUP],
    }));

    const onChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const {value, name} = e.target;
        console.log(name,': ',value);
        dispatch(
            changeField({
                form: 'signup',
                key: name,
                value,
            }),
        );
    }

    const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        // const data = new FormData(event.currentTarget);

        const {userId, name, email, userPassword, userPasswordConfirm} = form;

        // eslint-disable-next-line no-console
        console.log('submit 요청 : ', {userId, name, email, userPassword, userPasswordConfirm});

        signup({userId, name, email, userPassword, userPasswordConfirm});
    };

    useEffect(() => {
        dispatch(initializeForm('signup'));
    }, [dispatch]);

    useEffect(()=>{
        if(signUpError) {
            setError(signUpError);
        }
    }, [signUpError])

    const signup = useCallback((reqData) => {
        dispatch(signupSagaStart(reqData))
    }, [dispatch]);

    return <SignUp form = {form}/* signup={signup}*/ error={error} isLoading={isLoading} onChange={onChange} handleSubmit={handleSubmit}/>
}