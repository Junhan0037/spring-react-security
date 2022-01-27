import SignIn from "../components/SignIn";
import {useCallback, useEffect, useState} from "react";
import {useDispatch, useSelector} from "react-redux";
import {changeField, initializeForm, signin as signinSagaStart, SIGNIN} from "../redux-saga/modules/auth";
import {SignInErrorType, RootState} from "../types";
import * as React from "react";

export default function SignInContainer(){
    const [error, setError] = useState<SignInErrorType | null>(null);
    const dispatch = useDispatch();
    const {form, signInError, isLoading} = useSelector((state: RootState )=>({
        form: state.auth.signin,
        signInError: state.auth.signInError,
        isLoading: state.loading[SIGNIN],
    }));

    const onChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const {value, name} = e.target;
        console.log(name,': ',value);
        dispatch(
            changeField({
                form: 'signin',
                key: name,
                value,
            }),
        );
    }

    const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        // const data = new FormData(event.currentTarget);

        const {userId, userPassword} = form;

        // eslint-disable-next-line no-console
        console.log('submit 요청 : ', {userId, userPassword});

        signin({userId, userPassword});
    };

    useEffect(() => {
        dispatch(initializeForm('signin'));
    }, [dispatch]);

    useEffect(()=>{
        if(signInError) {
            setError(signInError);
            console.log(signInError)
        }
    }, [signInError])

    const signin = useCallback((reqData) => {
        dispatch(signinSagaStart(reqData))
    }, [dispatch]);

    return <SignIn form={form}/* signin={signin}*/ error={error} isLoading={isLoading} onChange={onChange} handleSubmit={handleSubmit}/>
}