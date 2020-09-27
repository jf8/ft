import axios from 'axios';

import buildPaginationQueryOpts from '@/shared/sort/sorts';

import { IVFtUserSignInfoFt } from '@/shared/model/v-ft-user-sign-info-ft.model';

const baseApiUrl = 'api/v-ft-user-sign-infos';

export default class VFtUserSignInfoFtService {
  public find(id: number): Promise<IVFtUserSignInfoFt> {
    return new Promise<IVFtUserSignInfoFt>((resolve, reject) => {
      axios
        .get(`${baseApiUrl}/${id}`)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public retrieve(paginationQuery?: any): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .get(baseApiUrl + `?${buildPaginationQueryOpts(paginationQuery)}`)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public delete(id: number): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .delete(`${baseApiUrl}/${id}`)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public create(entity: IVFtUserSignInfoFt): Promise<IVFtUserSignInfoFt> {
    return new Promise<IVFtUserSignInfoFt>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}`, entity)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public update(entity: IVFtUserSignInfoFt): Promise<IVFtUserSignInfoFt> {
    return new Promise<IVFtUserSignInfoFt>((resolve, reject) => {
      axios
        .put(`${baseApiUrl}`, entity)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
