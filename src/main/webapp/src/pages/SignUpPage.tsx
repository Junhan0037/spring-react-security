import * as React from 'react';
import SignInContainer from "../containers/SignInContainer";
import {useSelector} from "react-redux";
import {RootState, UserInfoType} from "../types";
import {Redirect} from "react-router";
import SignUpContainer from "../containers/SignUpContainer";


export default function SignUpPage() {
    const isSigninedIn = useSelector<RootState, boolean>(
        (state) => state.auth.isSigninedIn
    );

    if(isSigninedIn === true){
        return <Redirect to="/"/>;
    }

    return <SignUpContainer/>;
}

