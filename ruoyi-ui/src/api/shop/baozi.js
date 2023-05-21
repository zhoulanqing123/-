import request from '@/utils/request'

// 查询baozi列表
export function listBaozi(query) {
  return request({
    url: '/shop/baozi/list',
    method: 'get',
    params: query
  })
}

// 查询baozi详细
export function getBaozi(id) {
  return request({
    url: '/shop/baozi/' + id,
    method: 'get'
  })
}

// 新增baozi
export function addBaozi(data) {
  return request({
    url: '/shop/baozi',
    method: 'post',
    data: data
  })
}

// 修改baozi
export function updateBaozi(data) {
  return request({
    url: '/shop/baozi',
    method: 'put',
    data: data
  })
}

// 删除baozi
export function delBaozi(id) {
  return request({
    url: '/shop/baozi/' + id,
    method: 'delete'
  })
}

// 统计
export function baoZiTongJi(data) {
  return request({
    url: '/shop/baozi/tongJi',
    method: 'post',
    data: data

  })
}
