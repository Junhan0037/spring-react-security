import {SignInReqType} from "../types";
import client from "./client";

export default class UserService {
    public static async signin(reqData: SignInReqType): Promise<string> {
        const response = await client.post('/api/auth/sign-in', reqData)
        return response.data.token;
    }

    public static async logout(token: string): Promise<void> {
        await client.post('/api/auth/logout')
    }
}