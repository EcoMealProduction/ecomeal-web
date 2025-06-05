
import React from 'react';
import { Button } from '@/components/ui/button';
import { Link } from 'react-router-dom';
import { Smartphone, Download } from 'lucide-react';

export const CTASection = () => {
  return (
    <section className="py-20 bg-gradient-to-r from-green-600 to-green-700 text-white">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="grid lg:grid-cols-2 gap-12 items-center">
          {/* Left content */}
          <div className="text-center lg:text-left">
            <h2 className="text-4xl lg:text-5xl font-bold mb-6 leading-tight">
              Ready to Start Saving Food & Money?
            </h2>
            <p className="text-xl text-green-100 mb-8 leading-relaxed">
              Join thousands of users in Chisinau who are already making a difference. 
              Download EcoMeal now and start your sustainable food journey today.
            </p>

            {/* Benefits list */}
            <div className="space-y-3 mb-8">
              <div className="flex items-center gap-3">
                <div className="w-6 h-6 bg-green-400 rounded-full flex items-center justify-center">
                  <span className="text-green-800 font-bold text-sm">✓</span>
                </div>
                <span className="text-lg">Save up to 70% on delicious food</span>
              </div>
              <div className="flex items-center gap-3">
                <div className="w-6 h-6 bg-green-400 rounded-full flex items-center justify-center">
                  <span className="text-green-800 font-bold text-sm">✓</span>
                </div>
                <span className="text-lg">Help reduce food waste in your city</span>
              </div>
              <div className="flex items-center gap-3">
                <div className="w-6 h-6 bg-green-400 rounded-full flex items-center justify-center">
                  <span className="text-green-800 font-bold text-sm">✓</span>
                </div>
                <span className="text-lg">Discover new favorite local spots</span>
              </div>
            </div>

            {/* CTA Buttons */}
            <div className="flex flex-col sm:flex-row gap-4">
              <Link to="/onboarding">
                <Button size="lg" className="bg-white text-green-600 hover:bg-gray-100 text-lg px-8 py-4 h-auto">
                  <Smartphone className="mr-2" size={24} />
                  Get Started Now
                </Button>
              </Link>
              <Button 
                size="lg" 
                variant="outline" 
                className="border-white text-white hover:bg-white hover:text-green-600 text-lg px-8 py-4 h-auto"
              >
                <Download className="mr-2" size={24} />
                Download App
              </Button>
            </div>
          </div>

          {/* Right content - Phone mockup */}
          <div className="relative flex justify-center">
            <div className="relative">
              {/* Phone frame */}
              <div className="bg-gray-900 rounded-[3rem] p-2 transform rotate-6 hover:rotate-0 transition-transform duration-500">
                <div className="bg-white rounded-[2.5rem] p-6 w-80 h-[600px] overflow-hidden">
                  {/* App screenshot mockup */}
                  <div className="bg-green-600 rounded-t-2xl h-20 flex items-center justify-center text-white font-bold text-lg">
                    🌱 EcoMeal
                  </div>
                  <div className="p-4 space-y-4">
                    <div className="bg-gray-100 rounded-xl p-4">
                      <div className="flex items-center gap-3 mb-2">
                        <div className="w-8 h-8 bg-orange-200 rounded-full"></div>
                        <div className="text-sm font-medium">Bakery Fresh</div>
                      </div>
                      <div className="text-xs text-gray-600 mb-2">Assorted Pastries</div>
                      <div className="flex justify-between items-center">
                        <span className="text-lg font-bold text-green-600">25 lei</span>
                        <span className="text-xs bg-orange-100 text-orange-600 px-2 py-1 rounded">2h left</span>
                      </div>
                    </div>
                    <div className="bg-gray-100 rounded-xl p-4">
                      <div className="flex items-center gap-3 mb-2">
                        <div className="w-8 h-8 bg-green-200 rounded-full"></div>
                        <div className="text-sm font-medium">Green Café</div>
                      </div>
                      <div className="text-xs text-gray-600 mb-2">Sandwich Combo</div>
                      <div className="flex justify-between items-center">
                        <span className="text-lg font-bold text-green-600">40 lei</span>
                        <span className="text-xs bg-orange-100 text-orange-600 px-2 py-1 rounded">4h left</span>
                      </div>
                    </div>
                  </div>
                </div>
              </div>

              {/* Floating elements */}
              <div className="absolute -top-4 -right-4 bg-orange-500 text-white rounded-full p-3 animate-bounce">
                <span className="font-bold">NEW</span>
              </div>
              <div className="absolute -bottom-4 -left-4 bg-green-400 text-green-800 rounded-full p-3 animate-pulse">
                <Download size={24} />
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  );
};
