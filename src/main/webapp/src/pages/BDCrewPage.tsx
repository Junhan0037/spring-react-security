import React from "react";
import {Box, Container, ImageList, ImageListItem, ImageListItemBar} from "@mui/material";


export default function BDCrewPage() {
    const itemData = [
        {
            img: 'https://raw.githubusercontent.com/Junhan0037/spring-react-security/master/src/main/webapp/public/BDCrew.gif',
            title: '동현 가수 에디션',
        },
        // {
        //     img: 'https://raw.githubusercontent.com/Junhan0037/spring-react-security/master/src/main/webapp/public/BDCrew2.jpg',
        //     title: '동엽이의 은밀한 스파이더맨',
        // },
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
        {
            img: 'https://raw.githubusercontent.com/Junhan0037/spring-react-security/master/src/main/webapp/public/BDCrew27.jpg',
            title: '동현이 아오키지 에디션',
        },
        {
            img: 'https://raw.githubusercontent.com/Junhan0037/spring-react-security/master/src/main/webapp/public/BDCrew28.jpg',
            title: '노트북 미국갔어',
        },
        {
            img: 'https://raw.githubusercontent.com/Junhan0037/spring-react-security/master/src/main/webapp/public/BDCrew29.jpg',
            title: '병관아 힘내',
        },
        {
            img: 'https://raw.githubusercontent.com/Junhan0037/spring-react-security/master/src/main/webapp/public/BDCrew30.jpg',
            title: '키갈 마스터',
        },
        {
            img: 'https://raw.githubusercontent.com/Junhan0037/spring-react-security/master/src/main/webapp/public/BDCrew31.png',
            title: '동엽이는 문재인 대통령을 좋아한다',
        },
        {
            img: 'https://raw.githubusercontent.com/Junhan0037/spring-react-security/master/src/main/webapp/public/BDCrew32.jpg',
            title: '동현이 오리1',
        },
        {
            img: 'https://raw.githubusercontent.com/Junhan0037/spring-react-security/master/src/main/webapp/public/BDCrew33.jpg',
            title: '동현이 오리2',
        },
        {
            img: 'https://raw.githubusercontent.com/Junhan0037/spring-react-security/master/src/main/webapp/public/BDCrew34.jpg',
            title: '동현이 오리3',
        },
        {
            img: 'https://raw.githubusercontent.com/Junhan0037/spring-react-security/master/src/main/webapp/public/BDCrew35.png',
            title: '국민 키갈 남친 이동현',
        },
        {
            img: 'https://raw.githubusercontent.com/Junhan0037/spring-react-security/master/src/main/webapp/public/BDCrew36.webp',
            title: '오리에 미친 사람',
        },
        {
            img: 'https://raw.githubusercontent.com/Junhan0037/spring-react-security/master/src/main/webapp/public/BDCrew37.jpg',
            title: '동현이 여친',
        },
        {
            img: 'https://raw.githubusercontent.com/Junhan0037/spring-react-security/master/src/main/webapp/public/BDCrew38.jpg',
            title: '치킨 마시쩡',
        },
        {
            img: 'https://raw.githubusercontent.com/Junhan0037/spring-react-security/master/src/main/webapp/public/BDCrew39.jpg',
            title: '호두를 잡아버린 동현이',
        },
        {
            img: 'https://raw.githubusercontent.com/Junhan0037/spring-react-security/master/src/main/webapp/public/BDCrew40.jpg',
            title: '호두 잡은 동현이를 보는 윤만형',
        },
        {
            img: 'https://raw.githubusercontent.com/Junhan0037/spring-react-security/master/src/main/webapp/public/BDCrew41.jpg',
            title: '고추로 전화하는 동현이',
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