import SignIn from "../components/SignIn";
import {useCallback} from "react";
import {useDispatch} from "react-redux";
import {signin as signinSagaStart} from "../redux/modules/auth";

export default function SignInContainer(){
    const dispatch = useDispatch();

    const signin = useCallback((reqData) => {
        dispatch(signinSagaStart(reqData))
    }, [dispatch]);
    return <SignIn signin={signin}/>
}