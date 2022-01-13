import {SignInReqType, SignInResponseType} from "../../types";
import client from "../client";
import {AxiosResponse} from "axios";

export const signin = (reqData: SignInReqType) =>
    client.post('/api/auth/sign-in', null, {params: {userId: reqData.userId, userPassword: reqData.userPassword}})
        .then((response: AxiosResponse<SignInResponseType>) => {return response})
        .catch(function (error) {
                throw error.response;
        });

export const logout = () =>
    client.post('/api/auth/logout');
