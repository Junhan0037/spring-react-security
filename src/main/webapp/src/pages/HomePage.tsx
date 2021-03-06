import {useDispatch, useSelector} from "react-redux";
import {RootState} from "../types";
import {Redirect} from "react-router";
import {logout} from "../redux-saga/modules/auth";
import Copyright from "../components/Copyright";
import * as React from "react";

export default function HomePage() {
    const dispatch = useDispatch();

    const {isSigninedIn, userInfo} = useSelector((state: RootState )=>({
        isSigninedIn: state.auth.isSigninedIn,
        userInfo: state.auth.userInfo,
    }));

    if (isSigninedIn === false) {
        return <Redirect to="/signin"/>;
    }

    return (
        <div>
            <h1>Home</h1>
            <h2>로그인 정보 : {userInfo?.name}</h2>
            <button onClick={handleClick}>logout</button>
            <Copyright sx={{mt: 5}}/>
        </div>
    )

    function handleClick(){
        dispatch(logout());
    }
}