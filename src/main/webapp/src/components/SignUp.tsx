import * as React from 'react';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import Link from '@mui/material/Link';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import {createTheme, ThemeProvider} from '@mui/material/styles';
import {SignUpProps} from "../types";
import Loading from "./Loading";
import {Alert, AlertTitle} from "@mui/material";

function Copyright(props: any) {
    return (
        <Typography variant="body2" color="text.secondary" align="center" {...props}>
            {'Copyright © '}
            <Link color="inherit" href="/bd-crew" target="_blank" rel="noopener noreferrer">
                BD Crew
            </Link>{' '}
            {new Date().getFullYear()}
            {'.'}
        </Typography>
    );
}

const theme = createTheme();

// export default function SignUp() {
//   const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
//     event.preventDefault();
//     const data = new FormData(event.currentTarget);
//     // eslint-disable-next-line no-console
//     console.log({
//       userId: data.get("userId"),
//       name: data.get("name"),
//       email: data.get("email"),
//       password: data.get("password"),
//       passwordConfirm: data.get("passwordConfirm"),
//     });
//   };

const SignUp: React.FC<SignUpProps> = ({signup, error, isLoading}) => {

    const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        const data = new FormData(event.currentTarget);

        const reqData = {
            userId: data.get("userId"),
            name: data.get("name"),
            email: data.get("email"),
            userPassword: data.get("password"),
            userPasswordConfirm: data.get("passwordConfirm"),
        }
        // eslint-disable-next-line no-console
        console.log(reqData);

        signup(reqData);
    };

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
                        Sign up
                    </Typography>
                    <Box component="form" noValidate onSubmit={handleSubmit} sx={{mt: 3}}>
                        <Grid container spacing={2}>
                            <Grid item xs={12}>
                                <TextField
                                    required
                                    fullWidth
                                    id="email"
                                    label="Email Address"
                                    name="email"
                                    autoComplete="email"
                                    autoFocus
                                />
                            </Grid>
                            {/*<Grid item xs={12} sm={6}>*/}
                            {/*  <TextField*/}
                            {/*    autoComplete="given-name"*/}
                            {/*    name="firstName"*/}
                            {/*    required*/}
                            {/*    fullWidth*/}
                            {/*    id="firstName"*/}
                            {/*    label="First Name"*/}
                            {/*    autoFocus*/}
                            {/*  />*/}
                            {/*</Grid>*/}
                            <Grid item xs={12}>
                                <TextField
                                    required
                                    fullWidth
                                    id="name"
                                    label="name"
                                    name="name"
                                    autoComplete="family-name"
                                />
                            </Grid>
                            <Grid item xs={12}>
                                <TextField
                                    required
                                    fullWidth
                                    id="userId"
                                    label="User Id"
                                    name="userId"
                                    autoComplete="userId"
                                />
                            </Grid>
                            <Grid item xs={12}>
                                <TextField
                                    required
                                    fullWidth
                                    name="password"
                                    label="Password"
                                    type="password"
                                    id="password"
                                    autoComplete="new-password"
                                />
                            </Grid>
                            <Grid item xs={12} sx={{mb:3}}>
                                <TextField
                                    required
                                    fullWidth
                                    name="passwordConfirm"
                                    label="Password Confirm"
                                    type="password"
                                    id="passwordConfirm"
                                    autoComplete="new-password"
                                />
                            </Grid>
                            {/*<Grid item xs={12}>*/}
                            {/*    <FormControlLabel*/}
                            {/*        control={<Checkbox value="allowExtraEmails" color="primary"/>}*/}
                            {/*        label="I want to receive inspiration, marketing promotions and updates via email."*/}
                            {/*    />*/}
                            {/*</Grid>*/}
                        </Grid>
                        {/*<br/>*/}
                        {error &&
                            <Alert severity="error" style={{whiteSpace: 'pre-line',}}>
                                <AlertTitle><strong>Error:</strong></AlertTitle>
                                {error.message}

                                {/*아이디, 비밀번호를 확인해 주세요.*/}
                            </Alert>
                        }
                        <Button
                            type="submit"
                            fullWidth
                            variant="contained"
                            sx={{mt: 3, mb: 2}}
                        >
                            Sign Up
                        </Button>
                        <Grid container justifyContent="flex-end">
                            <Grid item>
                                <Link href="/signin" variant="body2">
                                    Already have an account? Sign in
                                </Link>
                            </Grid>
                        </Grid>
                    </Box>
                </Box>
                <Copyright sx={{mt: 5}}/>
            </Container>
            }
        </ThemeProvider>
    );
}

export default SignUp;