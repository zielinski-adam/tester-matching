import {Component, OnDestroy, OnInit} from '@angular/core';
import {Subscription} from "rxjs";
import {AppService} from "./core/services/app.service";
import {TesterRequest} from "./core/models/tester-request";
import {Tester} from "./core/models/tester";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit, OnDestroy {
  devicesSubscription: Subscription;
  countriesSubscription: Subscription;
  testersSubscription: Subscription;

  countries: string[] = ['GB', 'US', 'PL'];
  devices: string[] = ['Extra cheese', 'Dupa', 'Onion', 'Pepperoni', 'Sausage', 'Tomato'];
  selectedCountries: string[];
  selectedDevices: string[];
  testers: Tester[];
  displayedColumns: string[] = ['firstName', 'lastName', 'country', 'experience'];

  constructor(private appService: AppService) {
  }

  ngOnInit(): void {
    this.countriesSubscription = this.appService.findCountries().subscribe(
      (countries: string[]) => {
        if (countries) {
          this.appService.setCountries(countries);
          this.countries = countries;
        }
      }
    );

    this.devicesSubscription = this.appService.findDevices().subscribe(
      (devices: string[]) => {
        if (devices) {
          this.appService.setDevices(devices);
          this.devices = devices;
        }
      }
    );

  }

  ngOnDestroy(): void {
    this.devicesSubscription.unsubscribe();
    this.countriesSubscription.unsubscribe();
  }

  public receiveCountries($event: string[]): void {
    this.selectedCountries = $event;
  }

  public receiveDevices($event: string[]): void {
    this.selectedDevices = $event;
  }

  public findTesters(): void {
    let testerRequest = new TesterRequest();
    testerRequest.countries = this.selectedCountries;
    testerRequest.devices = this.selectedDevices;
    this.testersSubscription = this.appService.findTesters(testerRequest).subscribe(
      (testers: Tester[]) => {
        if (testers) {
          this.appService.setTesters(testers);
          this.testers = testers;
        }
      }
    );
  }


}
