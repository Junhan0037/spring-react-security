import React from "react";
import {Box, Container, ImageList, ImageListItem, ImageListItemBar} from "@mui/material";


export default function BDCrewPage() {
    const itemData = [
        {
            img: 'https://raw.githubusercontent.com/Junhan0037/spring-react-security/master/src/main/webapp/public/BDCrew.gif',
            title: '동현 가수 에디션',
        },
        {
            img: 'https://raw.githubusercontent.com/Junhan0037/spring-react-security/master/src/main/webapp/public/BDCrew2.jpg',
            title: '이상한 스파이더맨',
        },
        {
            img: 'https://raw.githubusercontent.com/Junhan0037/spring-react-security/master/src/main/webapp/public/BDCrew3.jpg',
            title: '동현 잠만보 에디션',
        },
        {
            img: 'https://raw.githubusercontent.com/Junhan0037/spring-react-security/master/src/main/webapp/public/BDCrew4.jpg',
            title: '동현 마미손 에디션',
        },
        {
            img: 'https://raw.githubusercontent.com/Junhan0037/spring-react-security/master/src/main/webapp/public/BDCrew5.jpg',
            title: '동현 똥꼬 에디션',
        },
        {
            img: 'https://raw.githubusercontent.com/Junhan0037/spring-react-security/master/src/main/webapp/public/BDCrew6.jpg',
            title: '준한형 여친 구함',
        },
        {
            img: 'https://raw.githubusercontent.com/Junhan0037/spring-react-security/master/src/main/webapp/public/BDCrew7.jpg',
            title: '민석형 마미손 에디션',
        },
        {
            img: 'https://raw.githubusercontent.com/Junhan0037/spring-react-security/master/src/main/webapp/public/BDCrew8.jpg',
            title: 'BD Crew 정모',
        },
        {
            img: 'https://raw.githubusercontent.com/Junhan0037/spring-react-security/master/src/main/webapp/public/BDCrew9.jpg',
            title: '민석형 마미손 에디션2',
        },
        {
            img: 'https://raw.githubusercontent.com/Junhan0037/spring-react-security/master/src/main/webapp/public/BDCrew10.jpg',
            title: '동현 기분문어',
        },
        {
            img: 'https://raw.githubusercontent.com/Junhan0037/spring-react-security/master/src/main/webapp/public/BDCrew11.jpg',
            title: '동현 먹방 에디션',
        },
        {
            img: 'https://raw.githubusercontent.com/Junhan0037/spring-react-security/master/src/main/webapp/public/BDCrew12.jpg',
            title: '말대꾸',
        },
        {
            img: 'https://raw.githubusercontent.com/Junhan0037/spring-react-security/master/src/main/webapp/public/BDCrew13.jpg',
            title: '동현 black 에디션',
        },
        {
            img: 'https://raw.githubusercontent.com/Junhan0037/spring-react-security/master/src/main/webapp/public/BDCrew14.jpg',
            title: '마미손 black',
        },
        {
            img: 'https://raw.githubusercontent.com/Junhan0037/spring-react-security/master/src/main/webapp/public/BDCrew15.jpg',
            title: '준한형 original',
        },
        {
            img: 'https://raw.githubusercontent.com/Junhan0037/spring-react-security/master/src/main/webapp/public/BDCrew16.jpg',
            title: 'shark and dick',
        },
        {
            img: 'https://raw.githubusercontent.com/Junhan0037/spring-react-security/master/src/main/webapp/public/BDCrew17.jpg',
            title: '병관 original',
        },
        {
            img: 'https://raw.githubusercontent.com/Junhan0037/spring-react-security/master/src/main/webapp/public/BDCrew18.jpg',
            title: '병관 and 민석형',
        },
        {
            img: 'https://raw.githubusercontent.com/Junhan0037/spring-react-security/master/src/main/webapp/public/BDCrew19.jpg',
            title: '동현 기행종 에디션',
        },
        {
            img: 'https://raw.githubusercontent.com/Junhan0037/spring-react-security/master/src/main/webapp/public/BDCrew20.png',
            title: '동현 이전 프로필',
        },
        {
            img: 'https://raw.githubusercontent.com/Junhan0037/spring-react-security/master/src/main/webapp/public/BDCrew21.jpg',
            title: '동현 and 준한형',
        },
        {
            img: 'https://raw.githubusercontent.com/Junhan0037/spring-react-security/master/src/main/webapp/public/BDCrew22.jpg',
            title: '동현 가수 에디션2',
        },
        {
            img: 'https://raw.githubusercontent.com/Junhan0037/spring-react-security/master/src/main/webapp/public/BDCrew23.jpg',
            title: '동현 CEO 에디션',
        },
        {
            img: 'https://raw.githubusercontent.com/Junhan0037/spring-react-security/master/src/main/webapp/public/BDCrew24.jpg',
            title: '동현 여장 에디션',
        },
        {
            img: 'https://raw.githubusercontent.com/Junhan0037/spring-react-security/master/src/main/webapp/public/BDCrew25.jpg',
            title: '돈스파이크 김흑암 에디션',
        },
        {
            img: 'https://raw.githubusercontent.com/Junhan0037/spring-react-security/master/src/main/webapp/public/BDCrew26.jpg',
            title: '아라베스크',
        },
    ];

    return (
        <Container maxWidth="xl" style={{alignContent: "center", justifyContent: "center"}}>

            <h1>BD Crew Photo History</h1>
            <Box>
                <ImageList variant="masonry" cols={5} gap={8}>
                    {itemData.map((item) => (
                        <ImageListItem key={item.img}>
                            <img
                                src={`${item.img}?w=248&fit=crop&auto=format`}
                                srcSet={`${item.img}?w=248&fit=crop&auto=format&dpr=2 2x`}
                                alt={item.title}
                                loading="lazy"
                            />
                            <ImageListItemBar position="below" title={item.title}/>
                        </ImageListItem>
                    ))}
                </ImageList>
            </Box>
        </Container>
    )
}