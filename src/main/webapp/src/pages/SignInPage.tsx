import * as React from 'react';
import SignInContainer from "../containers/SignInContainer";
import {useSelector} from "react-redux";
import {RootState} from "../types";
import {Redirect} from "react-router";


export default function SignInPage() {
    const token = useSelector<RootState, string | null>(
        (state) => state.auth.token
    );

    if(token !== null){
        return <Redirect to="/"/>;
    }

    return <SignInContainer/>;
}

