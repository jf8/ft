/* tslint:disable max-line-length */
import axios from 'axios';
import { format } from 'date-fns';

import * as config from '@/shared/config/config';
import { DATE_TIME_FORMAT } from '@/shared/date/filters';
import VFtUserSignInfoFtService from '@/entities/v-ft-user-sign-info-ft/v-ft-user-sign-info-ft.service';
import { VFtUserSignInfoFt } from '@/shared/model/v-ft-user-sign-info-ft.model';

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
  describe('VFtUserSignInfoFt Service', () => {
    let service: VFtUserSignInfoFtService;
    let elemDefault;
    let currentDate: Date;
    beforeEach(() => {
      service = new VFtUserSignInfoFtService();
      currentDate = new Date();

      elemDefault = new VFtUserSignInfoFt(
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        currentDate,
        currentDate,
        0
      );
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            startTime: format(currentDate, DATE_TIME_FORMAT),
            endTime: format(currentDate, DATE_TIME_FORMAT),
            updateTime: format(currentDate, DATE_TIME_FORMAT),
            createTime: format(currentDate, DATE_TIME_FORMAT),
            signTime: format(currentDate, DATE_TIME_FORMAT),
          },
          elemDefault
        );
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

      it('should create a VFtUserSignInfoFt', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            startTime: format(currentDate, DATE_TIME_FORMAT),
            endTime: format(currentDate, DATE_TIME_FORMAT),
            updateTime: format(currentDate, DATE_TIME_FORMAT),
            createTime: format(currentDate, DATE_TIME_FORMAT),
            signTime: format(currentDate, DATE_TIME_FORMAT),
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            startTime: currentDate,
            endTime: currentDate,
            updateTime: currentDate,
            createTime: currentDate,
            signTime: currentDate,
          },
          returnedFromService
        );

        mockedAxios.post.mockReturnValue(Promise.resolve({ data: returnedFromService }));
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not create a VFtUserSignInfoFt', async () => {
        mockedAxios.post.mockReturnValue(Promise.reject(error));

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a VFtUserSignInfoFt', async () => {
        const returnedFromService = Object.assign(
          {
            phoneCode: 'BBBBBB',
            phone: 'BBBBBB',
            email: 'BBBBBB',
            seat: 'BBBBBB',
            groupIds: 'BBBBBB',
            startTime: format(currentDate, DATE_TIME_FORMAT),
            endTime: format(currentDate, DATE_TIME_FORMAT),
            nameCn: 'BBBBBB',
            nameEn: 'BBBBBB',
            companyCn: 'BBBBBB',
            companyEn: 'BBBBBB',
            titleCn: 'BBBBBB',
            titleEn: 'BBBBBB',
            remark: 'BBBBBB',
            ddid: 'BBBBBB',
            updateTime: format(currentDate, DATE_TIME_FORMAT),
            createTime: format(currentDate, DATE_TIME_FORMAT),
            signTime: format(currentDate, DATE_TIME_FORMAT),
            meetId: 1,
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            startTime: currentDate,
            endTime: currentDate,
            updateTime: currentDate,
            createTime: currentDate,
            signTime: currentDate,
          },
          returnedFromService
        );
        mockedAxios.put.mockReturnValue(Promise.resolve({ data: returnedFromService }));

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a VFtUserSignInfoFt', async () => {
        mockedAxios.put.mockReturnValue(Promise.reject(error));

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of VFtUserSignInfoFt', async () => {
        const returnedFromService = Object.assign(
          {
            phoneCode: 'BBBBBB',
            phone: 'BBBBBB',
            email: 'BBBBBB',
            seat: 'BBBBBB',
            groupIds: 'BBBBBB',
            startTime: format(currentDate, DATE_TIME_FORMAT),
            endTime: format(currentDate, DATE_TIME_FORMAT),
            nameCn: 'BBBBBB',
            nameEn: 'BBBBBB',
            companyCn: 'BBBBBB',
            companyEn: 'BBBBBB',
            titleCn: 'BBBBBB',
            titleEn: 'BBBBBB',
            remark: 'BBBBBB',
            ddid: 'BBBBBB',
            updateTime: format(currentDate, DATE_TIME_FORMAT),
            createTime: format(currentDate, DATE_TIME_FORMAT),
            signTime: format(currentDate, DATE_TIME_FORMAT),
            meetId: 1,
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            startTime: currentDate,
            endTime: currentDate,
            updateTime: currentDate,
            createTime: currentDate,
            signTime: currentDate,
          },
          returnedFromService
        );
        mockedAxios.get.mockReturnValue(Promise.resolve([returnedFromService]));
        return service.retrieve({ sort: {}, page: 0, size: 10 }).then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of VFtUserSignInfoFt', async () => {
        mockedAxios.get.mockReturnValue(Promise.reject(error));

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a VFtUserSignInfoFt', async () => {
        mockedAxios.delete.mockReturnValue(Promise.resolve({ ok: true }));
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a VFtUserSignInfoFt', async () => {
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
