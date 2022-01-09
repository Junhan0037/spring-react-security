import {useDispatch, useSelector} from "react-redux";
import {RootState} from "../types";
import {Redirect} from "react-router";
import {logout} from "../redux/modules/auth";

export default function HomePage() {
    const dispatch = useDispatch();
    const token = useSelector<RootState, string | null>(
        (state) => state.auth.token
    );

    if (token === null) {
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