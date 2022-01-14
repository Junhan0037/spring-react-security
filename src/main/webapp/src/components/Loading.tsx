import * as React from "react";
import {Card, CardMedia, Modal} from "@mui/material";

const style = {
    position: 'absolute' as 'absolute',
    top: '50%',
    left: '50%',
    transform: 'translate(-50%, -50%)',
    width: 400,
    bgcolor: 'background.paper',
    border: 'none',
    boxShadow: 24,
    p: 4,
};

//수정 예정
const Loading: React.FC = () => {
    return (
        <Modal
            disableAutoFocus={true}
            open={true}
            aria-labelledby="modal-modal-title"
            aria-describedby="modal-modal-description"
        >
                <Card sx={style}>
                    <CardMedia

                        component="img"
                        image="https://raw.githubusercontent.com/Junhan0037/spring-react-security/master/src/main/webapp/public/spinner2.gif"
                    />
                    <CardMedia
                        component="img"
                        image="https://raw.githubusercontent.com/Junhan0037/spring-react-security/master/src/main/webapp/public/spinner1.gif"
                    />
                </Card>
        </Modal>
    );
}

export default Loading;