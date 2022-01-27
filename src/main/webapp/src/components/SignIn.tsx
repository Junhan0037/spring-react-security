import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import FormControlLabel from '@mui/material/FormControlLabel';
import Checkbox from '@mui/material/Checkbox';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import Link from '@mui/material/Link';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import * as React from "react";
import {SignInProps} from "../types";
import {Alert, AlertTitle} from "@mui/material";
import Loading from "./Loading";
import Copyright from './Copyright';

// function Copyright(props: any) {
//     return (
//         <Typography variant="body2" color="text.secondary" align="center" {...props}>
//             {'Copyright © '}
//             <Link color="inherit" href="/bd-crew" target="_blank" rel="noopener noreferrer">
//                 BD Crew
//             </Link>{' '}
//             {new Date().getFullYear()}
//             {'.'}
//         </Typography>
//     );
// }

const theme = createTheme();

const SignIn: React.FC<SignInProps> = ({form, /*signin, */error, isLoading, onChange, handleSubmit}) => {

    // const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
    //     event.preventDefault();
    //     const data = new FormData(event.currentTarget);
    //     const userId = data.get('userId');
    //     const userPassword = data.get('password');
    //
    //     // eslint-disable-next-line no-console
    //     // console.log({
    //     //     userId: userId,
    //     //     userPassword: userPassword,
    //     // });
    //
    //     signin({userId, userPassword});
    // };

    return (
        <ThemeProvider theme={theme}>
            {isLoading && <Loading/>}
            {!isLoading
            &&
            <Container component="main" maxWidth="xs">
                <CssBaseline/>
                <Box
                    sx={{
                        marginTop: 8,
                        display: 'flex',
                        flexDirection: 'column',
                        alignItems: 'center',
                    }}
                >
                    <Avatar sx={{m: 1, bgcolor: 'secondary.main'}}>
                        <LockOutlinedIcon/>
                    </Avatar>
                    <Typography component="h1" variant="h5">
                        Sign in
                    </Typography>
                    <Box component="form" onSubmit={handleSubmit} noValidate sx={{mt: 1}}>
                        <TextField
                            onChange={onChange}
                            value={form.userId}
                            margin="normal"
                            required
                            fullWidth
                            id="userId"
                            label="User Id"
                            name="userId"
                            autoComplete="userId"
                            autoFocus
                        />
                        <TextField
                            onChange={onChange}
                            value={form.userPassword}
                            margin="normal"
                            required
                            fullWidth
                            name="userPassword"
                            label="User Password"
                            type="password"
                            id="userPassword"
                            autoComplete="current-password"
                        />
                        <FormControlLabel
                            control={<Checkbox value="remember" color="primary"/>}
                            label="Remember me"
                        />
                        {error &&
                        <Alert severity="error" style={{whiteSpace: 'pre-line',}}>
                            <AlertTitle><strong>Error: </strong>{(error.code==='SECURITY003') && '로그인 실패'}{(error.networkStatus===500 && String(error.networkStatusText).includes('Server Error')) && '서버 오류'}</AlertTitle>
                            {(error.code==='SECURITY003') && 'ID, PASSWORD 를 확인해주세요.'}
                            {(error.networkStatus===500 && String(error.networkStatusText).includes('Server Error')) && '관리자에게 문의해주세요 (wnsgks0037@gmail.com)'}

                            {/*아이디, 비밀번호를 확인해 주세요.*/}

                            {/*{error.message?.split('.')[0]}.*/}
                            {/*<br/>*/}
                            {/*{error.message?.split('.')[1]}.*/}
                        </Alert>
                        }
                        <Button
                            type="submit"
                            fullWidth
                            variant="contained"
                            sx={{mt: 3, mb: 2,}}
                        >
                            Sign In
                        </Button>
                        <Grid container>
                            <Grid item xs>
                                <Link href="/find-pwd" variant="body2">
                                    Forgot password?
                                </Link>
                            </Grid>
                            <Grid item>
                                <Link href="/signup" variant="body2">
                                    {"Don't have an account? Sign Up"}
                                </Link>
                            </Grid>
                        </Grid>
                    </Box>
                </Box>
                <Copyright sx={{mt: 8, mb: 4}}/>
            </Container>
            }

        </ThemeProvider>
    )
}

export default SignIn;
