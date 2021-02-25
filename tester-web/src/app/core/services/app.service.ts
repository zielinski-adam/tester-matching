import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {BehaviorSubject, Observable} from "rxjs";
import {Tester} from "../models/tester";
import {TesterRequest} from "../models/tester-request";
import {environment} from "../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class AppService {

  private testers = new BehaviorSubject<Tester[]>(null);
  private countries = new BehaviorSubject<string[]>(null);
  private devices = new BehaviorSubject<string[]>(null);

  constructor(private httpClient: HttpClient) {
  }

  setTesters(testers: Tester[]): void {
    this.testers.next(testers);
  }

  getTesters(): Observable<Tester[]> {
    return this.testers.asObservable();
  }

  setCountries(countries: string[]): void {
    this.countries.next(countries);
  }

  getCountries(): Observable<string[]> {
    return this.countries.asObservable();
  }

  setDevices(devices: string[]): void {
    this.devices.next(devices);
  }

  getDevices(): Observable<string[]> {
    return this.devices.asObservable();
  }

  findTesters(testerRequest: TesterRequest): Observable<Tester[]> {
    return this.httpClient.post<Tester[]>(
      environment.prefix + '/testers', testerRequest
    );
  }

  findCountries(): Observable<string[]> {
    return this.httpClient.get<string[]>(
      environment.prefix + '/countries'
    );
  }

  findDevices(): Observable<string[]> {
    return this.httpClient.get<string[]>(
      environment.prefix + '/devices'
    );
  }

}
