import axios from 'axios';

import buildPaginationQueryOpts from '@/shared/sort/sorts';

import { ILiveSharingFt } from '@/shared/model/live-sharing-ft.model';

const baseApiUrl = 'api/live-sharings';

export default class LiveSharingFtService {
  public find(id: number): Promise<ILiveSharingFt> {
    return new Promise<ILiveSharingFt>((resolve, reject) => {
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

  public create(entity: ILiveSharingFt): Promise<ILiveSharingFt> {
    return new Promise<ILiveSharingFt>((resolve, reject) => {
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

  public update(entity: ILiveSharingFt): Promise<ILiveSharingFt> {
    return new Promise<ILiveSharingFt>((resolve, reject) => {
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
