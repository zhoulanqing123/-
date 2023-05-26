import request from '@/utils/request'

// 查询huajia列表
export function listHuajia(query) {
  return request({
    url: '/shop/huajia/list',
    method: 'get',
    params: query
  })
}

// 查询huajia详细
export function getHuajia(id) {
  return request({
    url: '/shop/huajia/' + id,
    method: 'get'
  })
}

// 新增huajia
export function addHuajia(data) {
  return request({
    url: '/shop/huajia',
    method: 'post',
    data: data
  })
}

// 修改huajia
export function updateHuajia(data) {
  return request({
    url: '/shop/huajia',
    method: 'put',
    data: data
  })
}

// 删除huajia
export function delHuajia(id) {
  return request({
    url: '/shop/huajia/' + id,
    method: 'delete'
  })
}

export function huaJiaTongJi(data) {
  return request({
    url: '/shop/huajia/tongJi',
    method: 'post',
    data: data

  })
}

export function huaJiaZheXianTongJi(data) {
    return request({
      url: '/shop/huajia/zheXianTongJi',
      method: 'post',
      data: data

    })
  }
