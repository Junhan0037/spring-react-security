import React from "react";
import {Box, Container, ImageList, ImageListItem} from "@mui/material";

export default function BDCrewPage(){
    const itemData = [
        {
            img: 'https://raw.githubusercontent.com/Junhan0037/spring-react-security/master/src/main/webapp/public/BDCrew.gif',
            title: 'Bed',
        },
        {
            img: 'https://raw.githubusercontent.com/Junhan0037/spring-react-security/master/src/main/webapp/public/BDCrew2.jpg',
            title: 'Bed',
        },
        {
            img: 'https://raw.githubusercontent.com/Junhan0037/spring-react-security/master/src/main/webapp/public/BDCrew3.jpg',
            title: 'Bed',
        },
        {
            img: 'https://raw.githubusercontent.com/Junhan0037/spring-react-security/master/src/main/webapp/public/BDCrew4.jpg',
            title: 'Bed',
        },
        {
            img: 'https://raw.githubusercontent.com/Junhan0037/spring-react-security/master/src/main/webapp/public/BDCrew5.jpg',
            title: 'Bed',
        },
        {
            img: 'https://raw.githubusercontent.com/Junhan0037/spring-react-security/master/src/main/webapp/public/BDCrew6.jpg',
            title: 'Bed',
        },
        {
            img: 'https://raw.githubusercontent.com/Junhan0037/spring-react-security/master/src/main/webapp/public/BDCrew7.jpg',
            title: 'Bed',
        },
        {
            img: 'https://raw.githubusercontent.com/Junhan0037/spring-react-security/master/src/main/webapp/public/BDCrew8.jpg',
            title: 'Bed',
        },
        {
            img: 'https://raw.githubusercontent.com/Junhan0037/spring-react-security/master/src/main/webapp/public/BDCrew9.jpg',
            title: 'Bed',
        },
        {
            img: 'https://raw.githubusercontent.com/Junhan0037/spring-react-security/master/src/main/webapp/public/BDCrew10.jpg',
            title: 'Bed',
        },
        {
            img: 'https://raw.githubusercontent.com/Junhan0037/spring-react-security/master/src/main/webapp/public/BDCrew11.jpg',
            title: 'Bed',
        },
        {
            img: 'https://raw.githubusercontent.com/Junhan0037/spring-react-security/master/src/main/webapp/public/BDCrew12.jpg',
            title: 'Bed',
        },
        {
            img: 'https://raw.githubusercontent.com/Junhan0037/spring-react-security/master/src/main/webapp/public/BDCrew13.jpg',
            title: 'Bed',
        },
        {
            img: 'https://raw.githubusercontent.com/Junhan0037/spring-react-security/master/src/main/webapp/public/BDCrew14.jpg',
            title: 'Bed',
        },
        {
            img: 'https://raw.githubusercontent.com/Junhan0037/spring-react-security/master/src/main/webapp/public/BDCrew15.jpg',
            title: 'Bed',
        },
        {
            img: 'https://raw.githubusercontent.com/Junhan0037/spring-react-security/master/src/main/webapp/public/BDCrew16.jpg',
            title: 'Bed',
        },
        {
            img: 'https://raw.githubusercontent.com/Junhan0037/spring-react-security/master/src/main/webapp/public/BDCrew17.jpg',
            title: 'Bed',
        },
        {
            img: 'https://raw.githubusercontent.com/Junhan0037/spring-react-security/master/src/main/webapp/public/BDCrew18.jpg',
            title: 'Bed',
        },
        {
            img: 'https://raw.githubusercontent.com/Junhan0037/spring-react-security/master/src/main/webapp/public/BDCrew19.jpg',
            title: 'Bed',
        },
        {
            img: 'https://raw.githubusercontent.com/Junhan0037/spring-react-security/master/src/main/webapp/public/BDCrew20.png',
            title: 'Bed',
        },
        {
            img: 'https://raw.githubusercontent.com/Junhan0037/spring-react-security/master/src/main/webapp/public/BDCrew21.jpg',
            title: 'Bed',
        },
        {
            img: 'https://raw.githubusercontent.com/Junhan0037/spring-react-security/master/src/main/webapp/public/BDCrew22.jpg',
            title: 'Bed',
        },
        {
            img: 'https://raw.githubusercontent.com/Junhan0037/spring-react-security/master/src/main/webapp/public/BDCrew23.jpg',
            title: 'Bed',
        },
        {
            img: 'https://raw.githubusercontent.com/Junhan0037/spring-react-security/master/src/main/webapp/public/BDCrew24.jpg',
            title: 'Bed',
        },
        {
            img: 'https://raw.githubusercontent.com/Junhan0037/spring-react-security/master/src/main/webapp/public/BDCrew25.jpg',
            title: 'Bed',
        },
        {
            img: 'https://raw.githubusercontent.com/Junhan0037/spring-react-security/master/src/main/webapp/public/BDCrew26.jpg',
            title: 'Bed',
        },
    ];

    return (
        <Container maxWidth="xl" style={{alignContent:"center", justifyContent:"center"}}>
            <Box >
                <ImageList variant="masonry" cols={5} gap={8}>
                    {itemData.map((item) => (
                        <ImageListItem key={item.img}>
                            <img
                                src={`${item.img}?w=248&fit=crop&auto=format`}
                                srcSet={`${item.img}?w=248&fit=crop&auto=format&dpr=2 2x`}
                                alt={item.title}
                                loading="lazy"
                            />
                        </ImageListItem>
                    ))}
                </ImageList>
            </Box>
            {/*<img width="25%" src="https://raw.githubusercontent.com/Junhan0037/spring-react-security/master/src/main/webapp/public/BDCrew.gif"/>*/}
            {/*<img width="25%" src="https://raw.githubusercontent.com/Junhan0037/spring-react-security/master/src/main/webapp/public/BDCrew2.jpg"/>*/}
            {/*<img width="25%" src="https://raw.githubusercontent.com/Junhan0037/spring-react-security/master/src/main/webapp/public/BDCrew3.jpg"/>*/}
            {/*<img width="25%" src="https://raw.githubusercontent.com/Junhan0037/spring-react-security/master/src/main/webapp/public/BDCrew4.jpg"/>*/}
            {/*<img width="25%" src="https://raw.githubusercontent.com/Junhan0037/spring-react-security/master/src/main/webapp/public/BDCrew5.jpg"/>*/}
            {/*<img width="25%" src="https://raw.githubusercontent.com/Junhan0037/spring-react-security/master/src/main/webapp/public/BDCrew6.jpg"/>*/}
        </Container>
    )
}