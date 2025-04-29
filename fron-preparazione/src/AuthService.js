import axios from 'axios';

const API_BASE_URL = "http://localhost:8080"

class AuthService{

    signup(user) {
        return axios.post(`${API_BASE_URL}/auth/signup`, user)
    }

    login(credentials){
        return axios.post(`${API_BASE_URL}/auth/login`, credentials)
    }
}

export default new AuthService(); 