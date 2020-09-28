/* tslint:disable max-line-length */
import axios from 'axios';

import * as config from '@/shared/config/config';
import {} from '@/shared/date/filters';
import DdUserFtService from '@/entities/dd-user-ft/dd-user-ft.service';
import { DdUserFt } from '@/shared/model/dd-user-ft.model';

const mockedAxios: any = axios;
const error = {
  response: {
    status: null,
    data: {
      type: null,
    },
  },
};

jest.mock('axios', () => ({
  get: jest.fn(),
  post: jest.fn(),
  put: jest.fn(),
  delete: jest.fn(),
}));

describe('Service Tests', () => {
  describe('DdUserFt Service', () => {
    let service: DdUserFtService;
    let elemDefault;
    beforeEach(() => {
      service = new DdUserFtService();

      elemDefault = new DdUserFt(
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        false,
        0,
        false,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        false,
        'AAAAAAA',
        false,
        false,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign({}, elemDefault);
        mockedAxios.get.mockReturnValue(Promise.resolve({ data: returnedFromService }));

        return service.find(123).then(res => {
          expect(res).toMatchObject(elemDefault);
        });
      });

      it('should not find an element', async () => {
        mockedAxios.get.mockReturnValue(Promise.reject(error));
        return service
          .find(123)
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should create a DdUserFt', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);

        mockedAxios.post.mockReturnValue(Promise.resolve({ data: returnedFromService }));
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not create a DdUserFt', async () => {
        mockedAxios.post.mockReturnValue(Promise.reject(error));

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a DdUserFt', async () => {
        const returnedFromService = Object.assign(
          {
            unionid: 'BBBBBB',
            remark: 'BBBBBB',
            userid: 'BBBBBB',
            isLeaderInDepts: 'BBBBBB',
            isBoss: true,
            hiredDate: 1,
            isSenior: true,
            tel: 'BBBBBB',
            department: 'BBBBBB',
            workPlace: 'BBBBBB',
            orderInDepts: 'BBBBBB',
            mobile: 'BBBBBB',
            errmsg: 'BBBBBB',
            active: true,
            avatar: 'BBBBBB',
            isAdmin: true,
            isHide: true,
            jobnumber: 'BBBBBB',
            name: 'BBBBBB',
            extattr: 'BBBBBB',
            stateCode: 'BBBBBB',
            position: 'BBBBBB',
            roles: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);
        mockedAxios.put.mockReturnValue(Promise.resolve({ data: returnedFromService }));

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a DdUserFt', async () => {
        mockedAxios.put.mockReturnValue(Promise.reject(error));

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of DdUserFt', async () => {
        const returnedFromService = Object.assign(
          {
            unionid: 'BBBBBB',
            remark: 'BBBBBB',
            userid: 'BBBBBB',
            isLeaderInDepts: 'BBBBBB',
            isBoss: true,
            hiredDate: 1,
            isSenior: true,
            tel: 'BBBBBB',
            department: 'BBBBBB',
            workPlace: 'BBBBBB',
            orderInDepts: 'BBBBBB',
            mobile: 'BBBBBB',
            errmsg: 'BBBBBB',
            active: true,
            avatar: 'BBBBBB',
            isAdmin: true,
            isHide: true,
            jobnumber: 'BBBBBB',
            name: 'BBBBBB',
            extattr: 'BBBBBB',
            stateCode: 'BBBBBB',
            position: 'BBBBBB',
            roles: 'BBBBBB',
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        mockedAxios.get.mockReturnValue(Promise.resolve([returnedFromService]));
        return service.retrieve({ sort: {}, page: 0, size: 10 }).then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of DdUserFt', async () => {
        mockedAxios.get.mockReturnValue(Promise.reject(error));

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a DdUserFt', async () => {
        mockedAxios.delete.mockReturnValue(Promise.resolve({ ok: true }));
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a DdUserFt', async () => {
        mockedAxios.delete.mockReturnValue(Promise.reject(error));

        return service
          .delete(123)
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });
    });
  });
});
