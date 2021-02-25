import {Component, EventEmitter, Input, Output} from '@angular/core';
import {FormControl} from '@angular/forms';

/** @title Select with multiple selection */
@Component({
  selector: 'select-multiple-example',
  templateUrl: 'select-multiple-example.component.html',
  styleUrls: ['select-multiple-example.component.css'],
})
export class SelectMultipleExampleComponent {
  parameters = new FormControl();

  @Input()
  public placeholder: string;

  @Input()
  public inputParameters: string[];

  @Output() selectedValuesEmitter = new EventEmitter<string[]>();

  public selectedValues: string[];


  dataChanged = (): void => {
    this.selectedValuesEmitter.emit(this.selectedValues);
  };
}
