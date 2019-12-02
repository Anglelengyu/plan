import axios from 'axios'
import {Message} from 'element-ui'

/*响应处理  request拦截器*/
axios.interceptors.response.use(data => {
  console.log(data)
  if (data.status && data.status == 200 && data.data.status == 500) {
    Message.error({message: data.data.msg})
  }
  if (data.data.msg) {
    Message.success({message: data.data.msg});
  }
  return data;
}, err => {
  if (err.response.status == 504 || err.response.status == 404) {
    Message.error({message: '服务器被吃了⊙﹏⊙∥'});
  } else if (err.response.status == 403) {
    Message.error({message: '权限不足,请联系管理员!'});
  } else if (err.response.status == 401) {
    Message.error({message: err.response.data.msg});
  } else {
    if (err.response.data.msg) {
      Message.error({message: err.response.data.msg});
    } else {
      Message.error({message: '未知错误!'});
    }
  }
})

let base = '';
export const postRequest = (url, params) => {
  return axios({
    method: 'post',
    url: `${base}${url}`,
    data: params,
    headers: {
      'Content-Type': 'application/json'
    }
  })
}
