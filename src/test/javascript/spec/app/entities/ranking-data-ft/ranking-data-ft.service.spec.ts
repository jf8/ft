/* tslint:disable max-line-length */
import axios from 'axios';
import { format } from 'date-fns';

import * as config from '@/shared/config/config';
import { DATE_TIME_FORMAT } from '@/shared/date/filters';
import RankingDataFtService from '@/entities/ranking-data-ft/ranking-data-ft.service';
import { RankingDataFt } from '@/shared/model/ranking-data-ft.model';

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
  describe('RankingDataFt Service', () => {
    let service: RankingDataFtService;
    let elemDefault;
    let currentDate: Date;
    beforeEach(() => {
      service = new RankingDataFtService();
      currentDate = new Date();

      elemDefault = new RankingDataFt(0, 'AAAAAAA', 0, 0, 0, 0, 0, currentDate, false);
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            day: format(currentDate, DATE_TIME_FORMAT),
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

      it('should create a RankingDataFt', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            day: format(currentDate, DATE_TIME_FORMAT),
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            day: currentDate,
          },
          returnedFromService
        );

        mockedAxios.post.mockReturnValue(Promise.resolve({ data: returnedFromService }));
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not create a RankingDataFt', async () => {
        mockedAxios.post.mockReturnValue(Promise.reject(error));

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a RankingDataFt', async () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            totalPeople: 1,
            signdPeople: 1,
            attendance: 1,
            orderNum: 1,
            parentId: 1,
            day: format(currentDate, DATE_TIME_FORMAT),
            isLeaf: true,
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            day: currentDate,
          },
          returnedFromService
        );
        mockedAxios.put.mockReturnValue(Promise.resolve({ data: returnedFromService }));

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a RankingDataFt', async () => {
        mockedAxios.put.mockReturnValue(Promise.reject(error));

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of RankingDataFt', async () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            totalPeople: 1,
            signdPeople: 1,
            attendance: 1,
            orderNum: 1,
            parentId: 1,
            day: format(currentDate, DATE_TIME_FORMAT),
            isLeaf: true,
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            day: currentDate,
          },
          returnedFromService
        );
        mockedAxios.get.mockReturnValue(Promise.resolve([returnedFromService]));
        return service.retrieve({ sort: {}, page: 0, size: 10 }).then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of RankingDataFt', async () => {
        mockedAxios.get.mockReturnValue(Promise.reject(error));

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a RankingDataFt', async () => {
        mockedAxios.delete.mockReturnValue(Promise.resolve({ ok: true }));
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a RankingDataFt', async () => {
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
