/* tslint:disable max-line-length */
import axios from 'axios';

import * as config from '@/shared/config/config';
import {} from '@/shared/date/filters';
import DdBookDeptFtService from '@/entities/dd-book-dept-ft/dd-book-dept-ft.service';
import { DdBookDeptFt } from '@/shared/model/dd-book-dept-ft.model';

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
  describe('DdBookDeptFt Service', () => {
    let service: DdBookDeptFtService;
    let elemDefault;
    beforeEach(() => {
      service = new DdBookDeptFtService();

      elemDefault = new DdBookDeptFt(
        0,
        'AAAAAAA',
        0,
        0,
        false,
        false,
        false,
        'AAAAAAA',
        'AAAAAAA',
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

      it('should create a DdBookDeptFt', async () => {
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

      it('should not create a DdBookDeptFt', async () => {
        mockedAxios.post.mockReturnValue(Promise.reject(error));

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a DdBookDeptFt', async () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            orderNum: 1,
            parentid: 1,
            createDeptGroup: true,
            autoAddUser: true,
            deptHiding: true,
            deptPermits: 'BBBBBB',
            userPermits: 'BBBBBB',
            outerDept: true,
            outerPermitDepts: 'BBBBBB',
            outerPermitUsers: 'BBBBBB',
            orgDeptOwner: 'BBBBBB',
            deptManagerUseridList: 'BBBBBB',
            sourceIdentifier: 'BBBBBB',
            ext: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);
        mockedAxios.put.mockReturnValue(Promise.resolve({ data: returnedFromService }));

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a DdBookDeptFt', async () => {
        mockedAxios.put.mockReturnValue(Promise.reject(error));

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of DdBookDeptFt', async () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            orderNum: 1,
            parentid: 1,
            createDeptGroup: true,
            autoAddUser: true,
            deptHiding: true,
            deptPermits: 'BBBBBB',
            userPermits: 'BBBBBB',
            outerDept: true,
            outerPermitDepts: 'BBBBBB',
            outerPermitUsers: 'BBBBBB',
            orgDeptOwner: 'BBBBBB',
            deptManagerUseridList: 'BBBBBB',
            sourceIdentifier: 'BBBBBB',
            ext: 'BBBBBB',
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        mockedAxios.get.mockReturnValue(Promise.resolve([returnedFromService]));
        return service.retrieve({ sort: {}, page: 0, size: 10 }).then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of DdBookDeptFt', async () => {
        mockedAxios.get.mockReturnValue(Promise.reject(error));

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a DdBookDeptFt', async () => {
        mockedAxios.delete.mockReturnValue(Promise.resolve({ ok: true }));
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a DdBookDeptFt', async () => {
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
