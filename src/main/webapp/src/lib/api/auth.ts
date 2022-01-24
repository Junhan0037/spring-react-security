import {SignInReqType, SignInResponseType, SignUpReqType, SignUpResponseType} from "../../types";
import client from "../client";
import {AxiosResponse} from "axios";

export const signup = (reqData: SignUpReqType) =>
    client.post('/api/auth/sign-up', null, {
        params: {
            email: reqData.email,
            name: reqData.name,
            userId: reqData.userId,
            userPassword: reqData.userPassword,
            userPasswordConfirm: reqData.userPasswordConfirm,
        }
    })
        .then((response: AxiosResponse<SignUpResponseType>) => {return response})
        .catch(function (error) {
            throw error.response;
        });


export const signin = (reqData: SignInReqType) =>
    client.post('/api/auth/sign-in', null, {params: {userId: reqData.userId, userPassword: reqData.userPassword}})
        .then((response: AxiosResponse<SignInResponseType>) => {return response})
        .catch(function (error) {
                throw error.response;
        });

export const logout = () =>
    client.post('/api/auth/logout');
