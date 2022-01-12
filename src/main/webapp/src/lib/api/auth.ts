import {SignInReqType, SignInResponseType} from "../../types";
import client from "../client";

export const signin = (reqData: SignInReqType) =>
    client.post('/api/auth/sign-in', null, {params: {userId: reqData.userId, userPassword: reqData.userPassword}});

export const logout = () =>
    client.post('/api/auth/logout');
