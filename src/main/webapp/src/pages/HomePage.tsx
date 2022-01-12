import {useDispatch, useSelector} from "react-redux";
import {RootState, UserInfoType} from "../types";
import {Redirect} from "react-router";
import {logout} from "../redux-saga/modules/auth";

export default function HomePage() {
    const dispatch = useDispatch();
    const isSigninedIn = useSelector<RootState, boolean>(
        (state) => state.auth.isSigninedIn
    );

    if (isSigninedIn === false) {
        return <Redirect to="/signin"/>;
    }

    return (
        <div>
            <h1>Home</h1>
            <button onClick={handleClick}>logout</button>
        </div>
    )

    function handleClick(){
        dispatch(logout());
    }
}