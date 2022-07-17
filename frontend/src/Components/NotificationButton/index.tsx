import axios from 'axios';
import { toast } from 'react-toastify';
import icon from '../../assets/img/notification-icon.svg';
import { BASE_URL } from '../../utils/requests';
import './styles.css';

type Props = {
    saleId: Number;
}

function handleClick (id: Number) {
    axios(`${BASE_URL}/sales/${id}/notification`)
    .then(response => {
        toast.info("SMS Enviado");
    })
}


function NotificationButton({saleId}: Props) {
    return (
        <div className="dsmeta-red-button" onClick={() => handleClick(saleId)}>
            <img src={icon} alt="" />
        </div>
    );
}

export default NotificationButton;