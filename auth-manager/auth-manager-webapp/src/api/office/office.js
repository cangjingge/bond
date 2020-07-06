import request from '@/utils/request'

export const officeService = {
  list: function(params) {
    return request({
      url: '/office/list',
      method: 'get',
      params
    }, { indices: false })
  },
  create: function(params) {
    return request({
      url: '/office/create',
      method: 'post',
      data: params
    })
  },
  delete: function(fileId) {
    return request({
      url: '/office/delete/' + fileId,
      method: 'delete'
    })
  }
}
