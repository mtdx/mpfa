/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { MmaTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { CsgoItemDetailComponent } from '../../../../../../main/webapp/app/entities/csgo-item/csgo-item-detail.component';
import { CsgoItemService } from '../../../../../../main/webapp/app/entities/csgo-item/csgo-item.service';
import { CsgoItem } from '../../../../../../main/webapp/app/entities/csgo-item/csgo-item.model';

describe('Component Tests', () => {

    describe('CsgoItem Management Detail Component', () => {
        let comp: CsgoItemDetailComponent;
        let fixture: ComponentFixture<CsgoItemDetailComponent>;
        let service: CsgoItemService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [MmaTestModule],
                declarations: [CsgoItemDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    CsgoItemService,
                    JhiEventManager
                ]
            }).overrideTemplate(CsgoItemDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(CsgoItemDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CsgoItemService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new CsgoItem(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.csgoItem).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
